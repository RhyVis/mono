package top.rhynia.monotrix.elements.data

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import top.rhynia.monotrix.interfaces.data.TextSimpleEntry

@Entity
@Table(name = "spam_max")
data class TextSpamMax(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Long,
    @Column(name = "text")
    override var text: String
) : TextSimpleEntry
