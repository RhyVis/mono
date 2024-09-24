package top.rhynia.monotrix.elements.data.text

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import top.rhynia.monotrix.interfaces.data.EntryText

@Entity
@Table(name = "gacha_gs")
data class EntryGachaGs(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Long,
    @Column(name = "text")
    override var text: String
) : EntryText
