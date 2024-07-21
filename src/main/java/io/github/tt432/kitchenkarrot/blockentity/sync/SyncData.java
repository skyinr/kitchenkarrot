package io.github.tt432.kitchenkarrot.blockentity.sync;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;

/**
 * @author DustW
 **/
public abstract class SyncData<V> {
    private V value;
    private boolean changed;
    private final String name;
    public final boolean needSave;

    public SyncData(String name, V defaultValue, boolean needSave) {
        this.value = defaultValue;
        this.name = name;
        this.needSave = needSave;
    }

    protected abstract CompoundTag toTag(HolderLookup.Provider provider);

    protected abstract V fromTag(HolderLookup.Provider provider, CompoundTag tag);

    @NotNull
    public V get() {
        return value;
    }

    public void set(V value) {
        this.value = value;
        onChanged();
    }

    public void save(HolderLookup.Provider provider, CompoundTag tag, boolean force) {
        if (changed || force) {
            tag.put(name, toTag(provider));
            changed = false;
        }
    }

    public void load(HolderLookup.Provider provider, CompoundTag tag) {
        if (tag.contains(name)) {
            value = fromTag(provider, tag.getCompound(name));
        }
    }

    protected void onChanged() {
        this.changed = true;
    }
}
