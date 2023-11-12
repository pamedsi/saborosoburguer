package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTO.combo.ComboDTO;
import saboroso.saborosoburguer.DTO.combo.ComboMapper;
import saboroso.saborosoburguer.entities.menuItems.accompaniment.Combo;
import saboroso.saborosoburguer.models.CRUDResponseMessage;
import saboroso.saborosoburguer.repositories.ComboRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ComboService {

    private final ComboRepository comboRepository;
    private final ComboMapper comboMapper;

    public ComboService(ComboRepository comboRepository, ComboMapper comboMapper) {
        this.comboMapper = comboMapper;
        this.comboRepository = comboRepository;
    }

    public CRUDResponseMessage createCombo(ComboDTO comboDTO) {
        Combo combo = new Combo(comboDTO);
        comboRepository.save(combo);
        return new CRUDResponseMessage(true, null, List.of(comboDTO.title() + "cadastrado!"));
    }
    public List<ComboDTO> getCombos() {
        List<Combo> combos = comboRepository.findAllByDeletedFalse();
        return comboMapper.severalToDTO(combos);
    }
    public CRUDResponseMessage editCombo(ComboDTO changes) {

        Combo comboToEdit = comboRepository.findByIdentifier(changes.identifier());
        if (comboToEdit == null) return new CRUDResponseMessage(false, "Combo não encontrada!", null);

        List<String> changesForResponse = new ArrayList<>();

        if (!Objects.equals(comboToEdit.getTitle(), changes.title())) {
            comboToEdit.setTitle(changes.title());
            changesForResponse.add("Título modificado! Agora se chama: " + changes.title());
        }
        if (!Objects.equals(comboToEdit.getDescription(), changes.description())) {
            comboToEdit.setDescription(changes.description());
            changesForResponse.add("Descrição modificado! Agora é: " + changes.title());
        }
        if (comboToEdit.getPrice().compareTo(changes.price()) != 0) {
            comboToEdit.setPrice(changes.price());
            changesForResponse.add("Preço alterado! Agora custa: " + changes.price());
        }
        if (!Objects.equals(comboToEdit.getPic(), changes.pic())) {
            comboToEdit.setPic(changes.pic());
            changesForResponse.add("Foto atualizada! Agora a URL é: " + changes.pic());
        }
        if (comboToEdit.getInStock() != changes.inStock()) {
            comboToEdit.setInStock(changes.inStock());
            if (changes.inStock()) changesForResponse.add(changes.title() + " disponibilizado!");
            else changesForResponse.add(changes.title() + " indisponibilizado!");
        }

        if (changesForResponse.isEmpty()) return new CRUDResponseMessage(false, "Nenhuma mudança solicitada é diferente dos dados já presentes!", null);
        comboRepository.save(comboToEdit);

        return new CRUDResponseMessage(true, null, changesForResponse);
    }

    public CRUDResponseMessage deleteCombo(String comboIdentifier) {
        try {
            if (comboIdentifier == null || comboIdentifier.length() != 36) return new CRUDResponseMessage(
                    false, "Identificador de combo inválido!", null
            );
            Combo comboToDelete = comboRepository.findByIdentifierAndDeletedFalse(comboIdentifier);
            comboToDelete.setDeleted(true);
            comboRepository.save(comboToDelete);
            return new CRUDResponseMessage(true, null, List.of(comboToDelete.getTitle() + " deletado!"));
        }
        catch (Exception exception) {
            return new CRUDResponseMessage(false, exception.toString(), null);
        }
    }

    public List<ComboDTO> getCombosForMenu() {
        List<Combo> combos = comboRepository.findAllByDeletedFalseAndInStockTrue();
        return comboMapper.severalToDTO(combos);
    }
}
