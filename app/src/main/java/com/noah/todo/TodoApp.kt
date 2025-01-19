package com.noah.todo

import android.app.Application
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.noah.todo.models.TodoModel
import com.noah.todo.ui.screens.TodoAddScreen
import com.noah.todo.ui.screens.TodoDetailsScreen
import com.noah.todo.ui.screens.TodoEditScreen
import com.noah.todo.ui.screens.TodoListScreen
import com.noah.todo.viewmodels.TodoAddViewModel
import com.noah.todo.viewmodels.TodoDetailsViewModel
import com.noah.todo.viewmodels.TodoEditViewModel
import com.noah.todo.viewmodels.TodoListViewModel
import kotlinx.serialization.Serializable

@Serializable
object TodoList

@Serializable
data class TodoView(val id:Int)

@Serializable
object TodoAdd

@Serializable
data class TodoDetails(val id:Int)

@Serializable
data class TodoEdit(val id:Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route?.substringAfter("/") ?: TodoList::class.qualifiedName
    val title = when (currentScreen) {
        TodoList::class.qualifiedName -> stringResource(id = R.string.todo_list)
        TodoAdd::class.qualifiedName -> stringResource(id = R.string.todo_add)
        TodoDetails::class.qualifiedName -> stringResource(id = R.string.todo_details)
        else -> ""
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                navigationIcon = {
                    if (navController.previousBackStackEntry != null) {
                        IconButton(onClick = {navController.navigateUp()}) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.go_back)
                            )
                        }
                    }
                }
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .windowInsetsPadding(WindowInsets.navigationBars)
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = TodoList,
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            composable<TodoList> {
                val app = LocalContext.current.applicationContext as Application
                val viewmodel:TodoListViewModel = viewModel() {
                    TodoListViewModel(app)
                }

                viewmodel.loadTodos()

                val addTodoClick = {
                    navController.navigate(TodoAdd)
                }

                val detailsTodoClick = { todo:TodoModel ->
                    navController.navigate(TodoDetails(todo.id))
                }

                val checkTodoClick = { todo:TodoModel ->
                    viewmodel.updateTodoCompletion(todo)
                }

                val deleteTodoClick = { todo:TodoModel ->
                    viewmodel.deleteTodo(todo)
                }

                TodoListScreen(
                    viewmodel,
                    addTodoClick,
                    checkTodoClick,
                    detailsTodoClick,
                    deleteTodoClick)
            }

            composable<TodoAdd> {
                val app = LocalContext.current.applicationContext as Application
                val viewmodel:TodoAddViewModel = viewModel() {
                    TodoAddViewModel(app)
                }

                val doneClick = {
                    viewmodel.saveTodo()
                    val bruh = navController.navigateUp()
                }

                TodoAddScreen(viewmodel, doneClick)
            }

            composable<TodoDetails> { backStackEntry ->
                val args:TodoDetails = backStackEntry.toRoute<TodoDetails>()

                val app = LocalContext.current.applicationContext as Application
                val viewmodel:TodoDetailsViewModel = viewModel() {
                    TodoDetailsViewModel(app)
                }

                viewmodel.loadTodoById(args.id)

                val checkTodoClick = { todo:TodoModel ->
                    viewmodel.updateTodoCompletion(todo)
                }

                val editTodoClick = { todo:TodoModel ->
                    navController.navigate(TodoEdit(todo.id))
                }

                TodoDetailsScreen(
                    viewmodel,
                    checkTodoClick,
                    editTodoClick,
                )
            }

            composable<TodoEdit> {backStackEntry ->
                val args:TodoDetails = backStackEntry.toRoute<TodoDetails>()

                val app = LocalContext.current.applicationContext as Application
                val viewmodel:TodoEditViewModel = viewModel() {
                    TodoEditViewModel(app)
                }

                viewmodel.loadTodoById(args.id)

                val doneClick = { todo:TodoModel ->
                    viewmodel.saveTodo(todo)
                    val bruh = navController.navigateUp()
                }

                TodoEditScreen(
                    viewmodel,
                    doneClick
                )
            }
        }
    }
}