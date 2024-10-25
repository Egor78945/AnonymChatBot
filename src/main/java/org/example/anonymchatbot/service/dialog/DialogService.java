package org.example.anonymchatbot.service.dialog;

import lombok.RequiredArgsConstructor;
import org.example.anonymchatbot.repository.DialogRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DialogService {
    private final DialogRepository dialogRepository;
}
