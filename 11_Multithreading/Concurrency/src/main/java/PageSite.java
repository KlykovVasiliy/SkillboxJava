import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PageSite extends RecursiveTask<List<String>> {
    private static final String homePageSite = Main.homePageSite;
    private static final List<String> listPagesSite = new ArrayList<>();
    private final String pageSite;

    public PageSite(String pageSite) {
        this.pageSite = pageSite;
    }

    @Override
    protected List<String> compute() {
        List<PageSite> listSubTasks = new ArrayList<>();
        List<String> tempList = getListAPages(this.pageSite);
        if (tempList.size() > 0) {
            for (String page : tempList) {
                if (!listPagesSite.contains(page)) {
                    PageSite task = new PageSite(page);
                    task.fork();
                    listSubTasks.add(task);
                    listPagesSite.add(page);
                }
            }
        }

        List<String> urls = new ArrayList<>();
        for (PageSite page : listSubTasks) {
            urls.addAll(page.join());
        }
        urls.stream().sorted().distinct()
                .filter(f -> (!listPagesSite.contains(f)))
                .forEach(listPagesSite::add);
        return listPagesSite;
    }

    private Document getSiteForParsePages(String urlPage) {
        Document document = null;
        try {
            Thread.sleep(100);
            document = Jsoup.connect(urlPage).maxBodySize(0).get();
        } catch (HttpStatusException e) {
            System.err.printf("Error get page of site. There is no page at this address %s%n",
                    e.getUrl());
        } catch (UnsupportedMimeTypeException e) {
            System.out.printf("URL address refers to the file: %s%n", e.getUrl());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    private List<String> getListAPages(String urlPage) {
        Document document = getSiteForParsePages(urlPage);
        if (document == null) {
            return new ArrayList<>();
        } else {
            Elements elements = document.getElementsByTag("a");
            return elements.stream()
                    .map(m -> m.attr("href"))
                    .filter(this::isChildPage)
                    .filter(this::isWithoutSymbolsQuestionOrStartPage)
                    .map(this::combinePartsOfTheUrl)
//                    .map(this::removeSlashFromEndOfLine)
                    .collect(Collectors.toList());
        }
    }

    private boolean isChildPage(String secondPart) {
        Pattern pattern = Pattern.compile(processingStringForRegularExpressions());
        Matcher matcher = pattern.matcher(secondPart);
        return matcher.find() || isFirstCharSlash(secondPart);
    }

    private boolean isFirstCharSlash(String secondPart) {
        Pattern pattern = Pattern.compile("^/");
        Matcher matcher = pattern.matcher(secondPart);
        return matcher.find();
    }

    private boolean isWithoutSymbolsQuestionOrStartPage(String pageSite) {
        Pattern pattern1 = Pattern.compile("\\?");
        Pattern pattern2 = Pattern.compile("#");
        Matcher matcher1 = pattern1.matcher(pageSite);
        Matcher matcher2 = pattern2.matcher(pageSite);
        return !(matcher1.find() || matcher2.find());
    }

    private String processingStringForRegularExpressions() {
        List<Character> listCharsStr = new ArrayList<>();
        listCharsStr.add('^');
        for (char ch : homePageSite.toCharArray()) {
            if (ch == '/' || ch == '.') {
                listCharsStr.add('\\');
            }
            listCharsStr.add(ch);
        }
        listCharsStr.add('.');
        listCharsStr.add('*');
        return listCharsStr.stream()
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    private String combinePartsOfTheUrl(String secondPart) {
        secondPart = secondPart.trim();
        if (isFirstCharSlash(secondPart) && (secondPart.length() == 1)) {
            return homePageSite;
        } else if (isFirstCharSlash(secondPart)) {
            return homePageSite + secondPart;
        } else {
            return secondPart;
        }
    }

    private String removeSlashFromEndOfLine(String url) {
        int positionLastSlash = url.lastIndexOf("/");
        String tempStr = url;
        if (tempStr.length() - 1 == positionLastSlash) {
            tempStr = tempStr.substring(0, positionLastSlash);
        }
        return tempStr;
    }
}