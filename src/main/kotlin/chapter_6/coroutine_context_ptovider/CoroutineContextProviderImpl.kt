package chapter_6.coroutine_context_ptovider

import kotlin.coroutines.CoroutineContext

class CoroutineContextProviderImpl(
    private val context: CoroutineContext
) : CoroutineContextProvider {

    override fun context(): CoroutineContext = context
}