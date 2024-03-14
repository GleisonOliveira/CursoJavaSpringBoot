package com.gleisonoliveira.personapi.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

public class ModelMapperMapper {
    private static ModelMapper modelMapper = new ModelMapper();
    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return modelMapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
        List<D> destinationObjects = new ArrayList<D>();

        for (O item : origin) {
            destinationObjects.add(modelMapper.map(item, destination));
        }
        return destinationObjects;
    }
}
