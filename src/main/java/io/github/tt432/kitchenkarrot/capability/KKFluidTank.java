package io.github.tt432.kitchenkarrot.capability;

import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

import java.util.function.Predicate;

public class KKFluidTank extends FluidTank {
    public KKFluidTank(int capacity, Predicate<FluidStack> predicate) {
        super(capacity, predicate);
    }

    public KKFluidTank(int capacity) {
        super(capacity);
    }
}
