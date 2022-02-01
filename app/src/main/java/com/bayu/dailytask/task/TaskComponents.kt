package com.bayu.dailytask.task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Mood
import androidx.compose.material.icons.rounded.PushPin
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun Tasks(
    items: List<Task>,
) {
    LazyColumn(
        modifier = Modifier
            .padding(top = 16.dp)
    ) {
        items(items) { item ->
            TaskItem(item = item)
        }
    }
}

@Composable
fun TaskItem(
    item: Task,
) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier
                    .weight(1F),
            ) {
                Text(
                    text = item.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.subtitle1
                )
                Text(
                    text = item.description,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body1,
                )
            }
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = if (item.isImportant) Icons.Rounded.PushPin else Icons.Rounded.Mood,
                    contentDescription = "Pin"
                )
            }
        }
    }
}

@Composable
fun TaskForms(
    title: String,
    onTitleChange: (String) -> Unit,
    desc: String,
    onDescChange: (String) -> Unit,
    isImportant: Boolean,
    onIsImportantChange: (Boolean) -> Unit,
    buttonSlots: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
    ) {
        OutlinedTaskForm(
            text = title,
            onTextChange = onTitleChange,
            singleLine = true,
        ) {
            Text(text = "Title")
        }
        OutlinedTaskForm(
            text = desc,
            onTextChange = onDescChange
        ) {
            Text(text = "Description (optional)")
        }
        TaskCheckbox(
            taskIsImportant = isImportant,
            setTaskIsImportant = onIsImportantChange
        ) {
            Text(text = "Important?")
        }
        buttonSlots()
    }
}

@Composable
fun OutlinedTaskForm(
    text: String,
    onTextChange: (String) -> Unit,
    singleLine: Boolean = false,
    label: @Composable () -> Unit,
) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        label = label,
        singleLine = singleLine,
    )
}

@Composable
fun TaskCheckbox(
    taskIsImportant: Boolean,
    setTaskIsImportant: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable () -> Unit,
) {
    Row(
        modifier = modifier
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = taskIsImportant,
            onCheckedChange = setTaskIsImportant
        )
        Spacer(modifier = Modifier.width(8.dp))
        label()
    }
}

@Composable
fun TaskIconButton(
    imageVector: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Icon(imageVector = imageVector, contentDescription = contentDescription)
    }
}

@Composable
fun AddTaskFab(
    onClick: () -> Unit,
) {
    FloatingActionButton(
        onClick = onClick,
    ) {
        Icon(
            imageVector = Icons.Rounded.Add,
            contentDescription = "Add Task"
        )
    }
}
