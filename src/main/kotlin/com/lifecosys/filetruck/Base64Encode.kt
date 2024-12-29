package com.lifecosys.filetruck

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi


/**
 *
 * @author [Young Gu](mailto:hyysguyang@gmail.com)
 */
class Base64Encode : AnAction() {
    @OptIn(ExperimentalEncodingApi::class)
    override fun actionPerformed(e: AnActionEvent) {
        val content=this::class.java.getResourceAsStream("/META-INF/rust-extension/native-debug-dependency/note.md").readAllBytes()
        val base64= Base64.encode(content)
        Messages.showInfoMessage("Encoded message:\n${base64}", "Base64 Encode")

    }
}
