package vis.rhynia.monotrix.elements.data.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "str_nav")
class StrNav(
    @Id
    val id: Long,
    @Column(name = "data")
    var data: String,
    @Column(name = "label")
    var label: String,
    @Column(name = "note")
    var note: String,
)
