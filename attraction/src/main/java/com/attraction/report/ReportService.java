package com.attraction.report;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReportService {

  private ReportRepository reportRepository;

  public void save(Report report) {
    reportRepository.save(report);
  }

  public Integer getNewId() {
    List<Report> reportsList = reportRepository.findAll();
    return reportsList.isEmpty() ? 0 : reportsList.get(reportsList.size() - 1).getId() + 1;
  }
}
