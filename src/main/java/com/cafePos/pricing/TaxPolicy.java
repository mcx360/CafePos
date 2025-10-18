package com.cafePos.pricing;

import com.cafePos.common.Money;

public interface TaxPolicy { Money taxOn(Money amount); }