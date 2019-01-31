package com.zacharysweigart.weatherapp.domain

interface Command<out T> {
    fun execute() : T
}