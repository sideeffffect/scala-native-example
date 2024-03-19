package com.example.gtkexample

import com.example.library.*
import gio.all.*
import glib.all.*
import gtk.all.*
import gtk.fluent.*

import scala.scalanative.unsafe.*

object Main:

  private val adder = Adder(42)

  private def printHello(widget: Ptr[GtkWidget], data: gpointer): Unit = Zone:
    implicit z =>
      val message = s"Hello ${adder.add(123)}.\n"
      g_print(toCString(message).asGString)

  private def callback(application: Ptr[GtkApplication], data: gpointer): Unit =

    val window = gtk_application_window_new(application)

    gtk_window_set_title(
      window.asInstanceOf[Ptr[GtkWindow]],
      c"Hello from Scala Native"
    )
    gtk_window_set_default_size(window.asPtr[GtkWindow], 200, 200)

    val box = gtk_box_new(GtkOrientation.GTK_ORIENTATION_VERTICAL, 0)
    gtk_widget_set_halign(box, GtkAlign.GTK_ALIGN_CENTER)
    gtk_widget_set_valign(box, GtkAlign.GTK_ALIGN_CENTER)

    gtk_window_set_child(window.asPtr[GtkWindow], box)

    val button = gtk_button_new_with_label(c"Press me")

    g_signal_connect(
      button,
      c"clicked",
      CFuncPtr2.fromScalaFunction(printHello)
    )

    gtk_box_append(box.asPtr[GtkBox], button)

    gtk_widget_show(window)

  end callback

  def main(args: Array[String]): Unit =

    gtk_init()

    val app = gtk_application_new(
      c"org.gtk.example",
      GApplicationFlags.G_APPLICATION_FLAGS_NONE
    ).asPtr[GApplication]

    g_signal_connect(app, c"activate", CFuncPtr2.fromScalaFunction(callback))

    g_application_run(app, 0, null)

  end main
