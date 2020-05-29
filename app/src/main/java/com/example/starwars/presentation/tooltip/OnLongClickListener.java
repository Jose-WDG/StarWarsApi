package com.example.starwars.presentation.tooltip;

import androidx.annotation.NonNull;

public interface OnLongClickListener {

    boolean onLongClick(@NonNull TooltipBuilder tooltipBuilder);
}
