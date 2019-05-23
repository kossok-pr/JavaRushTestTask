package com.example.testtask;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PartRepository extends PagingAndSortingRepository<Part, Integer> {
}