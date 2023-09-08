package chapter_5

import kotlinx.coroutines.*
import java.io.File


fun main() {
    val scope = CustomScope()

    val userId = 992

    scope.launch {
        println("Finding user")
        val userDeferred = getUserByIdFromNetwork(userId, scope)
        val usersFromFileDeferred = readUsersFromFile("users.txt", scope)

        val userStoredInFile = checkUserExists(
            userDeferred.await(), usersFromFileDeferred.await()
        )

        if (userStoredInFile) {
            println("Found user in file!")
        }
    }

    scope.onStop()
}

private fun getUserByIdFromNetwork(userId: Int, parentScope: CoroutineScope) = parentScope.async {
    if (!isActive) {
        return@async User(0, "", "")
    }
    println("Retrieving user from network")
    delay(3000)
    println("Still in the coroutine")

    User(userId, "Filip", "Babic")
}

data class User(
    val id: Int,
    val name: String,
    val lastName: String,
)

private fun <T> produceValue(scope: CoroutineScope): Deferred<T> = scope.async {
    var data: T? = null

    requestData<T> { value ->
        data = value
    }

    while (data == null) {

    }

    data!!
}

private fun <T> requestData(onDataReady: (T?) -> Unit) = onDataReady.invoke(null)


private fun readUsersFromFile(filePath: String, scope: CoroutineScope) = scope.async {
    println("Reading the file of users")
    delay(1000)

    File(filePath)
        .readLines()
        .asSequence()
        .filter { it.isNotEmpty() }
        .map {
            val data = it.split(" ")

            if (data.size == 3) data else emptyList()
        }
        .filter {
            it.isNotEmpty()
        }
        .map {
            val userId = it[0].toInt()
            val name = it[1]
            val lastName = it[2]

            User(userId, name, lastName)
        }
        .toList()
}

private fun checkUserExists(user: User, users: List<User>) = user in users