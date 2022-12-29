package com.example.projectonmvi.presentation

interface MainEvent

class SaveEvent(val text: String) : MainEvent

class LoadEvent : MainEvent
