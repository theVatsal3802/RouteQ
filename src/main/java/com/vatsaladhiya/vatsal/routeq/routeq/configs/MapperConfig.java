package com.vatsaladhiya.vatsal.routeq.routeq.configs;

import com.vatsaladhiya.vatsal.routeq.routeq.dtos.PointDTO;
import com.vatsaladhiya.vatsal.routeq.routeq.utils.GeometryUtil;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(PointDTO.class, Point.class)
                .setConverter(context -> {
                    PointDTO pointDTO = context.getSource();
                    return GeometryUtil.createPoint(pointDTO);
                });
        modelMapper.typeMap(Point.class, PointDTO.class)
                .setConverter(context -> {
                    Point point = context.getSource();
                    double[] coordinates = {
                            point.getX(),
                            point.getY()
                    };
                    return new PointDTO(coordinates);
                });
        return modelMapper;
    }
}
