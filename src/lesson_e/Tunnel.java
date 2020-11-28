package lesson_e;

import java.util.concurrent.Semaphore;

class Tunnel extends Stage {


    private static int MAX_TUNNEL_PLACES = 2;
    public static Semaphore semTunnel = new Semaphore(MAX_TUNNEL_PLACES);
    private static final boolean[] TUNNEL_PLACES = new boolean[MAX_TUNNEL_PLACES];


    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                semTunnel.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                int tunnelPlace = -1;
                synchronized (TUNNEL_PLACES){
                    for (int i = 0; i < MAX_TUNNEL_PLACES; i++)
                        if (!TUNNEL_PLACES[i]) {
                            TUNNEL_PLACES[i] = true;
                            tunnelPlace = i;
                            break;
                        }
                }
                Thread.sleep(length / c.getSpeed() * 1000);
                synchronized (TUNNEL_PLACES) {
                    TUNNEL_PLACES[tunnelPlace] = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                semTunnel.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
