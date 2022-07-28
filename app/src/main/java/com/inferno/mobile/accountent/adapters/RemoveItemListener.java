package com.inferno.mobile.accountent.adapters;

import com.inferno.mobile.accountent.models.User;

import java.io.Serializable;

public interface RemoveItemListener<T> extends Serializable {
    void onRemove(T user,int pos);
}
