package uk.co.vibe.viva.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paged {
    private int totalPages;
    private long totalElements;
    private int numberOfElements;
    private int pageSize;
    private int currentPage;
    private boolean isLast;
    private boolean isFirst;
    private boolean hasNext;
    private boolean hasPrevious;
    private boolean hasContent;

    public Paged(Page page) {
        this.totalPages = page.getTotalPages();
        this.currentPage = page.getNumber();
        this.pageSize = page.getSize();
        this.totalElements = page.getTotalElements();
        this.numberOfElements = page.getNumberOfElements();
        this.hasNext = page.hasNext();
        this.hasPrevious = page.hasPrevious();
        this.isFirst = page.isFirst();
        this.isLast = page.isLast();
        this.hasContent = page.hasContent();
    }
}
