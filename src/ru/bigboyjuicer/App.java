package ru.bigboyjuicer;

import ru.bigboyjuicer.Classes.GameServer;

public class App {

    public static void main(String[] args){

        GameServer gameServer = new GameServer();
        System.out.println(gameServer);

        for(int i = 0; i < 36; i++){
            gameServer.updateServer();
            try{
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(gameServer);
        }
    }
}
