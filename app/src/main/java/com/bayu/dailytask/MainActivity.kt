package com.bayu.dailytask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.bayu.dailytask.task.TaskScreen
import com.bayu.dailytask.task.TaskViewModel
import com.bayu.dailytask.ui.theme.DailyTaskTheme

class MainActivity : ComponentActivity() {

    private val viewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyTaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainActivityScreen(viewModel)
                }
            }
        }
    }
}

@Composable
private fun MainActivityScreen(viewModel: TaskViewModel) {
    TaskScreen(
        tasks = viewModel.tasks,
        onAddTask = viewModel::onAddTask,
    )
}
