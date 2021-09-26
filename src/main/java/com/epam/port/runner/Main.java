package com.epam.port.runner;

import com.epam.port.view.ViewerProvider;

/**
 * Порт. Корабли заходят в порт для загрузки или выгрузки контейнеров и швартуються к причалам.
 * У каждого причала может стоять только один корабль.
 * Контейнеры перегружаються с коробля на корабль или склад порта.
 * Число контейнеров не может превышать емкость склада или корабля.
 *
 * @author Pavel Sirotsin
 * @version 1.0 20 Sept 2021
 */

public class Main {
    public static void main(String[] args) {
        ViewerProvider.getInstance().getMenuViewer().run();

    }
}
