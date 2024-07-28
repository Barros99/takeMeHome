package com.interview.takemehome.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FotoUploadController {

    private static final String DIRETORIO_UPLOAD = "src/assests/upload";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFoto(@RequestParam("foto") MultipartFile foto)
            throws IOException {
        if (foto.isEmpty()) {
            return ResponseEntity.badRequest().body("Selecione uma foto para upload.");
        }

        String nomeFoto = foto.getOriginalFilename();
        Path caminhoArquivo = Paths.get(DIRETORIO_UPLOAD, nomeFoto);

        try {
            Files.write(caminhoArquivo, foto.getBytes());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Erro ao salvar a foto.");
        }

        String mensagemSucesso = String.format("Foto \"%s\" enviada com sucesso para o caminho: %s",
                nomeFoto, caminhoArquivo.toString());
        return ResponseEntity.status(HttpStatus.OK).body(mensagemSucesso);
    }
}

