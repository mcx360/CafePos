package com.cafePos.domain.pricing;

import com.cafePos.domain.common.Money;

public interface TaxPolicy { Money taxOn(Money amount); }