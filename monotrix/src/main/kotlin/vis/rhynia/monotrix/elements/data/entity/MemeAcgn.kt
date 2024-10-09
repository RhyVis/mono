package vis.rhynia.monotrix.elements.data.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import vis.rhynia.monotrix.interfaces.data.EntryText

@Entity
@Table(name = "meme_acgn")
class MemeAcgn(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Long,
    @Column(name = "text")
    override var text: String,
) : EntryText
