package com.example.projectonmvi.presentation

interface MainEvent {

    class SendEvent(text: String) : MainEvent

    class LoadEvent() : MainEvent
}