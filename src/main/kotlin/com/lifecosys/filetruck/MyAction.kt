package com.lifecosys.filetruck

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages


/**
 *
 * @author [Young Gu](mailto:hyysguyang@gmail.com)
 */
class MyAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        // Action被触发时执行的逻辑
        Messages.showInfoMessage("Hello from My Kotlin Action!", "My Kotlin Action")
    }
}
