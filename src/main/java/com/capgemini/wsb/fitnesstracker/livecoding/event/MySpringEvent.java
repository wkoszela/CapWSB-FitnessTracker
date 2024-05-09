package com.capgemini.wsb.fitnesstracker.livecoding.event;

import org.springframework.context.ApplicationEvent;

class MySpringEvent extends ApplicationEvent {

    private String myMessage;

    public MySpringEvent(final Object source, String myMessage) {
        super(source);
        this.myMessage = myMessage;

    }

    public String getMyMessage() {
        return myMessage;
    }

    public void setMyMessage(final String myMessage) {
        this.myMessage = myMessage;
    }

}
