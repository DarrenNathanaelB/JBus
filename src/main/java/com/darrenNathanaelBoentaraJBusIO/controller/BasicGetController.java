package com.darrenNathanaelBoentaraJBusIO.controller;

import com.darrenNathanaelBoentaraJBusIO.Account;
import com.darrenNathanaelBoentaraJBusIO.Algorithm;
import com.darrenNathanaelBoentaraJBusIO.dbjson.JsonTable;
import com.darrenNathanaelBoentaraJBusIO.dbjson.Serializable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**.
 * This interface is used to represent a basic get controller.
 * @author Darren Nathanael
 * @param <T> The type of entity that extends the {@code Serializable} interface.
 */
@RestController
@RequestMapping("/account")

public interface BasicGetController <T extends Serializable> {
    public abstract JsonTable<T> getJsonTable();

    /**
     * Retrieves a paginated list of entities.
     *
     * @author Darren Nathanael
     * @param page     The page number (starting from 0).
     * @param pageSize The size of each page.
     * @return A paginated list of entities.
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET )
    public default List<T> getPage (
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int pageSize
    ){
        return Algorithm.<T>paginate(getJsonTable(), page, pageSize, a -> true);
    }

    /**
     * Retrieves an entity by its ID.
     *
     * @param id The ID of the entity to retrieve.
     * @return The entity with the specified ID, or {@code null} if not found.
     */
    @GetMapping("/{id}")
    public default T getById (@PathVariable int id){
        return Algorithm.<T>find(getJsonTable(), t -> t.id == id);
    }
}


