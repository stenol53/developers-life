package com.voak.android.developerslife.services

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.voak.android.developerslife.exceptions.NetworkResponseException
import com.voak.android.developerslife.exceptions.WrongResponseCodeException
import com.voak.android.developerslife.network.Api
import javax.inject.Inject
import kotlin.jvm.Throws

open class ApiService @Inject constructor() {

    @Inject
    lateinit var api: Api

    /** Состояние загрузки
     * При выполнении запроса можно передать ключ для HashMap.
     * По этому ключу получаем LiveData<Boolean> и так мониторим состояние загрузки.
     */
    private val loadingStates: HashMap<String, MutableLiveData<Boolean>> = HashMap()

    suspend fun <ReturnType> executeOrNull(
            loadingStateKey: String? = null,
            request: suspend () -> retrofit2.Response<ReturnType>
    ) : ReturnType? {
        return try {
            execute(loadingStateKey, request)
        } catch (exception: Exception) {
            null
        }
    }

    /***
     * Выполняет запрос в корутине
     * Проверяет код ответа (успешный код - 200) и тело ответа
     *
     * @param loadingStateKey - ключ состояния загрузки. По нему из loadingStates можно получить LiveData<Boolean> и следить за загрузкой
     * @param request - suspend функция из интерфейса Api, которую нужно выполнить
     *
     * @return Возвращает тело ответа <ReturnType> только если код 200 и тело ответа != null
     * @throws NetworkResponseException если тело ответа пустое
     * @throws WrongResponseCodeException если код ответа не 200
     * @throws IOException выброшенные из Retrofit
     * Не перехватывает исключения выброшенные из Retrofit (и из MainInterceptorNew ErrorHandlerNew). Они отображаются и прокидываются дальше из MainInterceptorNew и ErrorHandlerNew
     *
     * **/
    @Throws(NetworkResponseException::class)
    suspend fun <ReturnType> execute(
            loadingStateKey: String? = null,
            request: suspend () -> retrofit2.Response<ReturnType>
    ) : ReturnType {
        val result = executeRaw(loadingStateKey, request)
        if (result.isSuccessful) {
            result.body()?.let {
                return it
            }?: throw NetworkResponseException("Unable to get data from ${result.raw().request().url()}. Response body is null", null, result.raw())
        } else {
            throw WrongResponseCodeException("Unable to get data from ${result.raw().request().url()}. Response code ${result.code()}", null, result.raw())
        }
    }

    suspend fun <ReturnType> executeRawOrNull(
            loadingStateKey: String? = null,
            request: suspend () -> retrofit2.Response<ReturnType>
    ) : retrofit2.Response<ReturnType>? {
        return try {
            executeRaw(loadingStateKey, request)
        } catch (exception: Exception) {
            null
        }
    }

    /***
     * Выполняет запрос в корутине
     *
     * @param loadingStateKey - ключ состояния загрузки. По нему из loadingStates можно получить LiveData<Boolean> и следить за загрузкой
     * @param request - suspend функция из интерфейса Api, которую нужно выполнить
     *
     * @return Возвращает полный ответ от Retrofit Response<ReturnType?>
     * @throws Exception выброшенные из Retrofit
     *
     * **/
    @Throws(Exception::class)
    suspend fun <ReturnType> executeRaw(
            loadingStateKey: String? = null,
            request: suspend () -> retrofit2.Response<ReturnType>
    ): retrofit2.Response<ReturnType> {
        val loadingState = getLoadingState(loadingStateKey)
        loadingState?.postValue(true)
        val response = try {
            request.invoke()
        } catch (exception: Exception) {
            loadingState?.postValue(false)
            throw exception
        }
        loadingState?.postValue(false)
        return response
    }

    fun createLoadingState(loadingStateKey: String) {
        val currentLoadingState = loadingStates.getOrElse(loadingStateKey) { null }

        if (currentLoadingState == null) {
            loadingStates[loadingStateKey] = MutableLiveData<Boolean>().apply { value = false }
        }
    }

    fun createAndGetLoadingState(loadingStateKey: String): LiveData<Boolean>? {
        val currentLoadingState = loadingStates.getOrElse(loadingStateKey) { null}

        return if (currentLoadingState == null) {
            loadingStates[loadingStateKey] = MutableLiveData<Boolean>().apply { value = false }
            getLoadingState(loadingStateKey)
        } else {
            currentLoadingState
        }
    }

    fun getLoadingState(loadingStateKey: String?): MutableLiveData<Boolean>? {
        return if (loadingStateKey != null) {
            loadingStates.getOrElse(loadingStateKey) { null }
        } else {
            null
        }
    }

}