package com.attraction.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

  @Autowired
  ReportRepository reportRepository;

  public void save(Report report) {
    reportRepository.save(report);
  }

  public Integer getNewId() {
    List<Report> reportsList = reportRepository.findAll();
    return reportsList.isEmpty() ? 0 : reportsList.get(reportsList.size() - 1).getId() + 1;
  }
}
