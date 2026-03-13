package src

fun main() {
	println("Welcome to Kotlin TODO CLI!")
	
	val tasks = mutableListOf<Task>()
	var nextId = 1
	
	while (true) {
		print("> ")
		val input = readlnOrNull() ?: break
		val parts = input.split(" ", limit = 2)
		val command = parts[0]
		val argument = if (parts.size > 1) parts[1] else ""
		
		when (command.lowercase()) {
			"add" -> {
				val task = Task(nextId++, argument)
				tasks.add(task)
				println("Added: $task")
			}
			"list" -> {
				if (tasks.isEmpty()) println("No tasks.")
				else tasks.forEach { t ->
					val status = if (t.completed) "completed" else "not completed"
					println("${t.id}. ${t.title} [$status]")
				}
			}
			"done" -> {
				val id = argument.toIntOrNull()
				val task = tasks.find { it.id == id }
				if (task != null) {
					task.completed = true
					println("Marked as done: ${task.title}")
				} else println("Task not found.")
			}
			"delete" -> {
				val id = argument.toIntOrNull()
				val removed = tasks.removeIf { it.id == id }
				if (removed) println("Deleted task $id") else println("Task not found.")
			}
			"exit" -> break
			else -> println("Unknown command: $command")
		}
	}
	
	println("Goodbye!")
}
