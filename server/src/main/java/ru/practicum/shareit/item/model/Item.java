package ru.practicum.shareit.item.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.model.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Boolean available;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(table = "items", name = "owner_id")
    @JsonIgnore
    private User owner;
    @OneToMany(mappedBy = "item")
    private List<Comment> comments = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(table = "items", name = "request_id", updatable = false)
    @JsonIgnore
    private ItemRequest itemRequest;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                ", ownerId=" + (owner == null ? null : owner.getId()) +
                ", itemRequestId=" + (itemRequest == null ? null : itemRequest.getId()) +
                '}';
    }
}
