package threadcommunication.single;

import java.util.LinkedList;

/**
 * @author husky
 * @date 2019/7/12 11:21
 */
public class EventQueue {
    private final int max;

    static class Event{

    }
    private final LinkedList<Event> events = new LinkedList<>();

    private final static Integer DEDAULT_MAX_EVENT = 10;

    public EventQueue(int max) {
        this.max = max;
    }

    public EventQueue(){
        this(DEDAULT_MAX_EVENT);
    }

    public void offer(Event event){
        synchronized (events){
            if(events.size() >= DEDAULT_MAX_EVENT){
                try {
                    console("the event list is full,wait consumer consume");
                    events.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            console("the event is submit");
            events.addLast(event);
            events.notify();
        }
    }

    public void take(){
        synchronized (events){
            if(events.size() <= 0){

                try {
                    console("the event list is empty,wait producer produce");
                    events.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Event eventFirst  = events.getFirst();
            events.removeFirst();
            console("Event is " + eventFirst + "handler");
            events.notify();
        }
    }

    public static void console(String msg){
        System.out.println(msg);
    }
}
