package ru.practicum.shareit.booking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bookings")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_date")
    private LocalDateTime start;
    @Column(name = "end_date")
    private LocalDateTime end;
    @ManyToOne
    @JoinColumn(table = "bookings", name = "item_id")
    private Item item;
    @ManyToOne
    @JoinColumn(table = "bookings", name = "booker_id")
    private User booker;
    @Enumerated(value = EnumType.STRING)
    private BookingStatus status;

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", start=" + start +
                ", end=" + end +
                ", itemId=" + (item == null ? null : item.getId()) +
                ", bookerId=" + (booker == null ? null : booker.getId()) +
                ", status=" + status +
                '}';
    }
}
