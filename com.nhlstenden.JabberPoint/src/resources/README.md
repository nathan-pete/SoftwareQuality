# SoftwareQuality

This GitHub repository is the project submission for the Software Quality Course for Academic Year 2025-2026

## Student Information

Name: Nathan P. Pais D'costa
<br>
Student Number: 5045800

Name: Stefani Margaritova
<br>
Student Number: 4967399

## About this Branch

This branch (`main`) contains the refactored version of jabberpoint.JabberPoint. Starting from the original baseline
(see the `JabberpointV1` branch), this version applies four GoF design patterns and one interface segregation fix to
resolve a set of SOLID principle violations identified in the original design.

## The Five Refactoring Changes

| # | Pattern / Fix         | Target Class(es)                                                                         | Resolves                       |
|---|-----------------------|------------------------------------------------------------------------------------------|--------------------------------|
| 1 | Interface split (ISP) | `dataaccess.Accessor` → `dataaccess.PresentationLoader` / `dataaccess.PresentationSaver` | ISP, LSP                       |
| 2 | Factory               | `dataaccess.XMLAccessor` (loading)                                                       | OCP                            |
| 3 | Strategy              | `domain.SlideItem` subtypes, `dataaccess.XMLAccessor` (saving)                           | OCP                            |
| 4 | Observer              | `domain.Presentation`, `ui.SlideViewerComponent`                                         | Decouples UI from domain model |
| 5 | ui.Command            | `ui.MenuController`                                                                      | SRP, DIP                       |

## Contents

This branch contains the original 14 Java source files plus new classes and interfaces introduced by the refactor,
organized into the same four broad areas as the original, with persistence renamed below to reflect its
expanded structure.

| Area                      | Classes                                                                                                                                                                                                                                  |
|---------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Domain model              | `domain.Presentation`, `domain.Slide`, `domain.SlideItem`, `domain.TextItem`, `domain.BitmapItem`, `domain.Style`                                                                                                                        |
| Data access (persistence) | `dataaccess.Accessor`, `dataaccess.XMLAccessor`, `dataaccess.DemoPresentation`, `dataaccess.PresentationLoader`, `dataaccess.PresentationSaver`, plus a `domain.SlideItem` factory for loading and per-type save logic for serialization |
| User interface            | `ui.SlideViewerFrame`, `ui.SlideViewerComponent`, `ui.MenuController`, `ui.KeyController`, `ui.AboutBox`, plus a set of `ui.Command` classes (one per menu action)                                                                       |
| Entry point               | `jabberpoint.JabberPoint`                                                                                                                                                                                                                |

## What Changed From the Original

- **`dataaccess.Accessor` split into `dataaccess.PresentationLoader` and `dataaccess.PresentationSaver`.**
  `dataaccess.DemoPresentation` now implements only
  `dataaccess.PresentationLoader`, since it never supported saving. This removes the broken `saveFile` that used to
  throw an
  exception unconditionally.
- **`dataaccess.XMLAccessor`'s slide-item loading uses a Factory.** Adding a new kind of `domain.SlideItem` no longer
  requires
  editing `dataaccess.XMLAccessor` directly.
- **`dataaccess.XMLAccessor`'s slide-item saving uses Strategy.** Each `domain.SlideItem` is responsible for its own
  serialization,
  rather than `dataaccess.XMLAccessor` checking each item's concrete type.
- **`domain.Presentation` and `ui.SlideViewerComponent` use Observer.** The view registers as an observer and is
  notified
  when the current slide changes, instead of being tightly coupled to `domain.Presentation`.
- **`ui.MenuController`'s menu actions use ui.Command.** Each action (Open, Save, New, Exit, Next, Prev, GoTo, About) is
  its
  own `ui.Command` object. `ui.MenuController` only builds menus and binds commands; it no longer performs file I/O or
  constructs `dataaccess.XMLAccessor` directly.

## What the Application Does

Functionally unchanged from the original: opens a demo or XML-loaded presentation, displays one slide at a time,
supports navigation and file open/save/new via the menu, a "Go to slide" function, and an About dialog. The refactor
changes internal structure, not external behavior.

## Running

Compile and run `jabberpoint.JabberPoint.main`, optionally passing an XML presentation file as the first argument.
With no argument, the built-in demo presentation loads.
