package co.edu.unbosque.ridesmartv2.config;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelMapper {

    private final org.modelmapper.ModelMapper mp;

    public ModelMapper() {
        this.mp = new org.modelmapper.ModelMapper();
    }

    public <S, T> T map(S source, Class<T> targetClass) {
        return mp.map(source, targetClass);
    }

    public <S, T> List<T> mapList(List<S> sourceList, Class<T> targetClass) {
        return sourceList.stream()
                .map(element -> mp.map(element, targetClass))
                .collect(Collectors.toList());
    }}
