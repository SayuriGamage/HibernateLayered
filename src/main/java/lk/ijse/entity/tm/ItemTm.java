package lk.ijse.entity.tm;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode



public class ItemTm {
    private  String code;
    private  String description;
    private String unitPrice;
    private String qty;
}
