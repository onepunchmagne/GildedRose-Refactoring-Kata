package com.gildedrose;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class GildedRoseTest {

    @Test
    public void shouldBehaveLikeGoldenMaster() {
        GildedRose refactoring = new GildedRose(ItemsProvider.get());
        GildedRoseGolden golden = new GildedRoseGolden(ItemsProvider.get());

        for (int day = 0; day < 100; day++) {
            refactoring.updateQuality();
            golden.updateQuality();

            assertThat(refactoring.items)
                    .extracting(item -> item.sellIn)
                    .containsExactly(Arrays.stream(golden.items).map(item -> item.sellIn).toArray(Integer[]::new));
            assertThat(refactoring.items)
                    .extracting(item -> item.quality)
                    .containsExactly(Arrays.stream(golden.items).map(item -> item.quality).toArray(Integer[]::new));
        }
    }
}