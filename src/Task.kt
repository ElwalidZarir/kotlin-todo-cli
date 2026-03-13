package src

data class Task(
	val id: Int,
	val title: String,
	var completed: Boolean = false
)