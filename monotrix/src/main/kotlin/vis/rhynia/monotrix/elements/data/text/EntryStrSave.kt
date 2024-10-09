package vis.rhynia.monotrix.elements.data.text

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "str_save")
data class EntryStrSave(
    @Id
    val id: Long,
    @Column(name = "text")
    var text: String,
    @Column(name = "note")
    var note: String,
)
