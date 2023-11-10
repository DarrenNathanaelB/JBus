package com.darrenNathanaelBoentaraJBusIO.controller;

import com.darrenNathanaelBoentaraJBusIO.Account;
import com.darrenNathanaelBoentaraJBusIO.Algorithm;
import com.darrenNathanaelBoentaraJBusIO.dbjson.JsonTable;
import com.darrenNathanaelBoentaraJBusIO.dbjson.Serializable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/account")

public interface BasicGetController <T extends Serializable> {
    public abstract JsonTable<T> getJsonTable();
    @RequestMapping(value = "/page", method = RequestMethod.GET )
    public default List<T> getPage (
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int pageSize
    ){
        return Algorithm.<T>paginate(getJsonTable(), page, pageSize, a -> true);
    }

    @GetMapping("/{id}")
    public default T getById (@PathVariable int id){
        return Algorithm.<T>find(getJsonTable(), t -> t.id == id);
    }
}


