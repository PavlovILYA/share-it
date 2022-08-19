package ru.practicum.shareit.item.repository;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.exception.ItemNotFoundException;
import ru.practicum.shareit.item.exception.WrongItemOwnerException;
import ru.practicum.shareit.item.model.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class FakeItemRepositoryImpl implements ItemRepository {
    private final Map<Long, Item> items = new HashMap<>();
    private long nextId;

    @Override
    public Item save(Item item) {
        item.setId(generateId());
        items.put(item.getId(), item);
        return item;
    }

    @Override
    public Item update(Item item) {
        Item storedItem = get(item.getId());
        if (!item.getOwner().equals(storedItem.getOwner())) {
            throw new WrongItemOwnerException("Correct owner is "
                    + storedItem.getOwner()
                    + " but there is "
                    + item.getOwner());
        }
        if (item.getName() != null) {
            storedItem.setName(item.getName());
        }
        if (item.getDescription() != null) {
            storedItem.setDescription(item.getDescription());
        }
        if (item.getAvailable() != null) {
            storedItem.setAvailable(item.getAvailable());
        }
        return storedItem;
    }

    @Override
    public Item get(Long id) {
        Item item = items.get(id);
        if (item == null) {
            throw new ItemNotFoundException("Item " + id + " not found");
        } else {
            return item;
        }
    }

    @Override
    public List<Item> getAllByUserId(Long userId) {
        return items.values().stream()
                .filter(item -> item.getOwner().getId() == userId)
                .collect(Collectors.toList());
    }

    private Long generateId() {
        return ++nextId;
    }
}
