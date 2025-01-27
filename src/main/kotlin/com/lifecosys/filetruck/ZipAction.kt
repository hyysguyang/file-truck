package com.lifecosys.filetruck

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.ide.CopyPasteManager
import com.intellij.openapi.project.BaseProjectDirectories.Companion.getBaseDirectories
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFile
import java.awt.datatransfer.StringSelection
import java.io.FileOutputStream
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import kotlin.io.encoding.ExperimentalEncodingApi

fun zipDirectory(sourceDir: String, zipFile: String) {
    val sourcePath = Paths.get(sourceDir)
    ZipOutputStream(FileOutputStream(zipFile)).use { zos ->
        Files.walk(sourcePath).forEach { path ->
            val zipEntry = getZipEntry(sourcePath, path)
            if (zipEntry != null) {
                zos.putNextEntry(zipEntry)
                if (!Files.isDirectory(path)) {
                    Files.copy(path, zos)
                }
                zos.closeEntry()
            }
        }
    }
}

private fun getZipEntry(sourcePath: Path, path: Path): ZipEntry? {
    val relativePath = sourcePath.relativize(path)
    return if (relativePath.toString().isNotEmpty()) {
        ZipEntry(relativePath.toString().replace("\\", "/"))
    } else {
        null
    }
}


/**
 *
 * @author [Young Gu](mailto:hyysguyang@gmail.com)
 */
class ZipAction : AnAction() {
    @OptIn(ExperimentalEncodingApi::class)
    override fun actionPerformed(e: AnActionEvent) {

        val selectedFile: VirtualFile? = e.getData(CommonDataKeys.VIRTUAL_FILE)

        if (selectedFile != null && selectedFile.isDirectory) {
            val sourceDir = selectedFile.path

            val projectBaseDir = e.project?.basePath ?: ".";
            val tempDir = "$projectBaseDir/.idea/temp"
            Path.of(tempDir).toFile().mkdirs();

            val zipFile = "$tempDir/${selectedFile.nameWithoutExtension}.zip"

            try {
                zipDirectory(sourceDir, zipFile)
                val stringSelection = StringSelection(zipFile)
                CopyPasteManager.getInstance().setContents(stringSelection)
            } catch (e: IOException) {
                Messages.showErrorDialog("Error zipping directory: ${e.message}", "Zip Error")
            }
        } else {
            Messages.showWarningDialog("Please select a directory to zip", "Warning")
        }
    }

}
