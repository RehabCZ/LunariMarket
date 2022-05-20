package cz.lunari.lunarimarket.managers;

import cz.lunari.lunarimarket.LunariMarket;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class AbstractManager {

    protected final LunariMarket plugin;
}
