package com.bosonit.staffit.conocimientos.model.store;

import com.bosonit.staffit.conocimientos.configurations.shared.StringPrefixedSequenceIdGenerator;
import com.bosonit.staffit.conocimientos.model.book.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mstr_store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_seq")
    @GenericGenerator(
            name = "store_seq",
            strategy = "com.bosonit.staffit.conocimientos.configurations.shared.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "STORE"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%08d")
            })
    @Column(name = "store_id")
    private String storeId;

    @Column(name = "name")
    private String name;

    @Column(name = "direction")
    private String direction;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
    private List<Book> stock;
}
