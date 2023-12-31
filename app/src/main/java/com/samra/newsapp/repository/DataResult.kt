package com.samra.newsapp.repository

sealed class DataResult{
    class Success<T> (var data : T) : DataResult()
    class Failure (var message: String) : DataResult()
    class FailureHTTP (var message: String): DataResult()
}
