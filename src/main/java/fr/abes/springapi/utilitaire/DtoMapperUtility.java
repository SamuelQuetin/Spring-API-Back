package fr.abes.springapi.utilitaire;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//extrait de hello abes
@Service
public class DtoMapperUtility {

    @Autowired
    private ModelMapper modelMapper;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public <S, T> T map(S source, Class<T> targetClass) {
        Object tmpSource = source;
        if(source == null){
            tmpSource = new Object();
        }
        return modelMapper.map(tmpSource, targetClass);
    }
}
