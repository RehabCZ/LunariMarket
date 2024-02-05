package cz.lunari.lunarimarket.objects;

import cz.lunari.lunarimarket.LunariMarket;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class AbstractObject {

    protected final LunariMarket plugin;
}
